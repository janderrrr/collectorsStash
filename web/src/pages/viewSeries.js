import CollectorStashClient from '../api/collectorStashClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class ViewSeries extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'displaySeries', 'removeSeries', 'navigateToViewAllComicBooks'], this);
        this.dataStore = new DataStore();
        this.client = new CollectorStashClient();
        this.header = new Header(this.dataStore);
        console.log("viewSeries constructor");
    }

    async clientLoaded() {
        try {
            console.log("Client loaded is being called..");
            const result = await this.client.getSeries();
            console.log("Result in clientLoaded", result);

            if (result && result.series) {
                const series = result.series;
                this.dataStore.set('series', series);

                // Call the displaySeries method after setting the series data
                this.displaySeries();
            } else {
                console.error("Invalid series data in the result:", result);
            }
        } catch (error) {
            console.error("Error while loading Series:", error);
        }
    }

    displaySeries() {
        console.log("displaySeries called");
        const seriesData = this.dataStore.get('series');

        // Check if seriesData is defined and not null
        if (!seriesData) {
            console.error("Series data is undefined or null.");
            return;
        }

        const seriesContainer = document.getElementById('series');

        seriesContainer.innerHTML = '';

        seriesData.forEach((seriesItem) => {
            const seriesElement = document.createElement('div');
            seriesElement.className = 'series-item';

            const titleParagraph = document.createElement('p');
            titleParagraph.innerHTML = `<strong>Series Title:</strong> <strong>${seriesItem.title || 'N/A'}</strong>`;

            const volumeNumberParagraph = document.createElement('p');
            volumeNumberParagraph.innerHTML = `<strong>Volume Number:</strong> <strong>${seriesItem.volumeNumber || 'N/A'}</strong>`;

            const removeButton = document.createElement('button');
            removeButton.innerText = 'Remove';
            removeButton.className = 'remove-button';
            // Add a data attribute to store series ID for each remove button
            removeButton.dataset.seriesId = seriesItem.seriesId || seriesItem.id;

            const updateButton = document.createElement('button');
            updateButton.innerText = 'Update';
            updateButton.className = 'update-button';
            // Add a data attribute to store series ID for each update button
            updateButton.dataset.seriesId = seriesItem.seriesId || seriesItem.id;

            const viewAllComicBooksButton = document.createElement('button');
            viewAllComicBooksButton.innerText = 'View All Comic Books';
            viewAllComicBooksButton.className = 'view-all-comic-books-button';
            // Add a data attribute to store series ID for each View All Comic Books button
            viewAllComicBooksButton.dataset.seriesId = seriesItem.seriesId || seriesItem.id;

            seriesElement.appendChild(titleParagraph);
            seriesElement.appendChild(volumeNumberParagraph);
            seriesElement.appendChild(removeButton);
            seriesElement.appendChild(updateButton);
            seriesElement.appendChild(viewAllComicBooksButton);

            seriesContainer.appendChild(seriesElement);

            // Ensure customerId and errorCallback are accessible within this scope
            const customerId = seriesItem.customerId;
            const errorCallback = (error) => {
                console.error('Error:', error);
                // Handle the error as needed
            };

            // Attach a click event listener to each remove button for removal
            removeButton.addEventListener('click', (event) => {
                const seriesId = event.target.dataset.seriesId;
                this.removeSeries(seriesId, customerId, errorCallback);
            });

            // Attach a click event listener to each update button for navigation
            updateButton.addEventListener('click', async (event) => {
                const seriesId = event.target.dataset.seriesId;
                const isConfirmed = confirm('Are you sure you want to update this series?');

                if (isConfirmed) {
                    this.handleUpdateSeries(seriesId);
                }
            });

            // Attach a click event listener to each View All Comic Books button for navigation
            viewAllComicBooksButton.addEventListener('click', async (event) => {
                const seriesId = event.target.dataset.seriesId;
                this.navigateToViewAllComicBooks(seriesId);
            });
        });
    }

    // Function to handle series updates and navigation to updateSeries.html
    handleUpdateSeries(seriesId) {
        // Redirect to the update page with the seriesId
        window.location.href = 'updateSeries.html?seriesId=' + encodeURIComponent(seriesId);
    }

    // Function to handle navigation to viewAllComicBooks.html
    navigateToViewAllComicBooks(seriesId) {
        // Redirect to the viewAllComicBooks page with the seriesId
        window.location.href = 'viewAllComicBooks.html?seriesId=' + encodeURIComponent(seriesId);
    }

async removeSeries(seriesId) {
    // Get the series data for the specified seriesId
    const seriesData = this.dataStore.get('series');
    const seriesToRemove = seriesData.find(seriesItem => seriesItem.seriesId === seriesId);

    if (!seriesToRemove) {
        console.error(`Series with ID ${seriesId} not found.`);
        return;
    }

    // Display a confirmation prompt with information about the series to be removed
    const isConfirmed = confirm(`Are you sure you want to remove the series '${seriesToRemove.title}'?`);

    if (!isConfirmed) {
        return; // If not confirmed, do nothing
    }

    // Proceed with series removal
    try {
        console.log(`Removing series with ID: ${seriesId}`);
        const result = await this.client.removeSeries(seriesId);

        // Check if result is defined and has 'success' property
        if (result && result.success) {
            console.log("Series removed successfully");

            // Display a success message using an alert
            alert(`The series '${seriesToRemove.title}' has been successfully removed.`);

            await this.clientLoaded(); // Reload series after removal
        } else {
            // Check if result is defined and has 'error' property
            const errorMessage = result && result.error ? result.error : "Unknown error";
            console.error("Error while removing series:", errorMessage);
        }
    } catch (error) {
        console.error("Error while removing series:", error);
    }
}

    mount() {
        this.header.addHeaderToPage();
        this.clientLoaded();
    }
}

const main = async () => {
    const viewSeries = new ViewSeries();
    viewSeries.mount();
};

window.addEventListener('DOMContentLoaded', main);