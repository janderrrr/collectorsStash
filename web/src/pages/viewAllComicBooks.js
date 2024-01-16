import CollectorStashClient from '../api/collectorStashClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class ViewAllComicBooks extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'displayComics', 'createPropertyDiv', 'filterByFavorite', 'applyFilter'], this);
        this.dataStore = new DataStore();
        this.client = new CollectorStashClient();
        this.header = new Header(this.dataStore);
        console.log("viewAllComicBooks constructor");

        // Call clientLoaded after the client has been initialized
        this.clientLoaded();
    }

    async clientLoaded() {
        try {
            // Extract seriesId from the URL
            const urlParams = new URLSearchParams(window.location.search);
            const seriesId = urlParams.get('seriesId');

            // Check if seriesId is present
            if (!seriesId) {
                console.error("Series ID is missing in the URL.");
                return;
            }

            // Fetch comicbooks data for the specified seriesId
            const result = await this.client.getAllComicBooks(seriesId);
            console.log("Result in the clientLoaded", result);

            // Check if comicbooks array is present in the result
            if (result && result.comicbooks) {
                // Store comicbooks data in the DataStore
                this.dataStore.set('comicbooks', result.comicbooks);

                // Retrieve the selected filter value
                let selectedFilter;

                // Attach event listener to the filter dropdown button
                const filterButton = document.getElementById('filter');
                if (filterButton) {
                    filterButton.addEventListener('change', () => {
                        selectedFilter = filterButton.value;
                        this.applyFilter(selectedFilter);
                    });
                } else {
                    console.error("Filter dropdown not found.");
                }

                // Attach event listener to the Apply Filter button
                const applyFilterButton = document.querySelector('input[type="submit"]');
                if (applyFilterButton) {
                    applyFilterButton.addEventListener('click', (event) => {
                        event.preventDefault(); // Prevent the default form submission behavior
                        selectedFilter = filterButton.value;
                        this.applyFilter(selectedFilter);
                    });
                } else {
                    console.error("Apply Filter button not found.");
                }

                // Get the series details for the specified seriesId
                const seriesDetails = await this.client.getSeries(seriesId);

                // Update the header with the Comic Title and Volume Number
                const comicTitleElement = document.getElementById('comicTitle');
                const volumeNumberElement = document.getElementById('volumeNumber');

                if (seriesDetails && seriesDetails.series && seriesDetails.series.length > 0) {
                    const series = seriesDetails.series.find(s => s.seriesId === seriesId);

                    if (series) {
                        comicTitleElement.textContent = `Comic Books - ${series.title || ''}`;
                        volumeNumberElement.textContent = `Volume: ${series.volumeNumber || ''}`;
                    } else {
                        console.error("Series with the specified seriesId not found:", seriesDetails);
                    }
                } else {
                    console.error("Series details not found or empty:", seriesDetails);
                }

                // Once the data is loaded and event listeners are attached, display the comics
                this.displayComics(selectedFilter);
            } else {
                console.error("Comic books data is missing in the result:", result);
            }
        } catch (error) {
            console.error("Error in clientLoaded:", error);
        }
    }

displayComics(selectedFilter) {
    console.log("displayComics called");
    const comicBooksData = this.dataStore.get('comicbooks');

    // Check if comicBooksData is defined and not null
    if (!comicBooksData) {
        console.error("Comic books data is undefined or null.");
        return;
    }

    // Filter the comic books based on user selection
    let filteredComicBooks;

    switch (selectedFilter) {
        case 'favorite':
            filteredComicBooks = this.filterByFavorite();
            break;
        default:
            // Default case (filter by issueNumber)
            filteredComicBooks = comicBooksData.slice();
            break;
    }

    // Sort the comic books by issueNumber by default
    filteredComicBooks.sort((a, b) => a.issueNumber - b.issueNumber);

    // Update the title dynamically based on the first comic's title
    const firstComicTitle = filteredComicBooks.length > 0 ? filteredComicBooks[0].title : 'Comic Books';
    document.getElementById('comicTitle').innerText = firstComicTitle;

    const comicBooksContainer = document.getElementById('comicBooksContainer');

    // Clear the existing content
    comicBooksContainer.innerHTML = '';

    // Create a new container for the sorted comic books
    const newComicBooksContainer = document.createElement('div');
    newComicBooksContainer.id = 'comicBooksContainer';

    filteredComicBooks.forEach((comic) => {
        const comicElement = document.createElement('div');
        comicElement.className = 'comic-book';

        comicElement.style.display = 'grid';
        comicElement.style.gridTemplateColumns = '1fr 1fr';
        // Create a hidden div for seriesId
        const seriesIdDiv = this.createPropertyDiv('Series ID:', comic.seriesId || 'N/A');
        seriesIdDiv.style.display = 'none'; // Hide the seriesId
        const issueNumberDiv = this.createPropertyDiv('Issue Number:', comic.issueNumber || 'N/A');
        const yearDiv = this.createPropertyDiv('Year:', comic.year || 'N/A');
        const priceDiv = this.createPriceDiv('Price: $', comic.price || 'N/A'); // Use createPriceDiv for price
        const isFavoriteDiv = this.createPropertyDiv('Favorite?', comic.favorite || 'N/A');
        const publisherDiv = this.createPropertyDiv('Publisher:', comic.publisher || 'N/A');

        comicElement.appendChild(seriesIdDiv);
        comicElement.appendChild(issueNumberDiv);
        comicElement.appendChild(yearDiv);
        comicElement.appendChild(priceDiv);
        comicElement.appendChild(isFavoriteDiv);
        comicElement.appendChild(publisherDiv);

        newComicBooksContainer.appendChild(comicElement);
    });

    // Append the new container to the parent
    comicBooksContainer.parentNode.replaceChild(newComicBooksContainer, comicBooksContainer);
}

createPriceDiv(propertyText, valueText) {
    const priceDiv = document.createElement('div');
    priceDiv.className = 'property';

    const valueDiv = document.createElement('div');
    valueDiv.className = 'value';

    // Display the property name along with the value for price
    priceDiv.textContent = `${propertyText} ${valueText || 'N/A'}`;

    priceDiv.appendChild(valueDiv);

    return priceDiv;
}

    // Helper method to create property and value divs
    createPropertyDiv(propertyText, valueText) {
        const propertyDiv = document.createElement('div');
        propertyDiv.className = 'property';

        const valueDiv = document.createElement('div');
        valueDiv.className = 'value';

        // Check if the value is a boolean and display 'Yes' or 'No' accordingly
        const displayValue = typeof valueText === 'boolean' ? (valueText ? 'Yes' : 'No') : valueText;

        // For price, display only the value without the property name
        if (propertyText.toLowerCase() === 'price') {
            valueDiv.textContent = `${displayValue || 'N/A'}`;
        } else {
            valueDiv.textContent = `${propertyText} ${displayValue || 'N/A'}`;
        }

        propertyDiv.appendChild(valueDiv);

        return propertyDiv;
    }

    filterByFavorite() {
        const comicBooksData = this.dataStore.get('comicbooks');

        if (comicBooksData) {
            // Filter the comic books based on favorites
            const favoriteComicBooks = comicBooksData.filter(comic => comic.favorite);

            // Update the stored comicbooks data with the filtered data
            this.dataStore.set('comicbooks', favoriteComicBooks);

            // Display the comics with the updated data
            this.displayComics();
        } else {
            console.error("Comic books data is undefined or null.");
        }
    }

    applyFilter() {
        try {
            // Retrieve the seriesId from the URL
            const urlParams = new URLSearchParams(window.location.search);
            const seriesId = urlParams.get('seriesId');

            // Check if seriesId is present
            if (!seriesId) {
                console.error("Series ID is missing in the URL.");
                return;
            }

            // Retrieve the selected filter value
            const selectedFilter = document.getElementById('filter').value;

            switch (selectedFilter) {
                case 'favorite':
                    this.filterByFavorite();
                    break;
                default:
                    // Default case (no filtering)
                    break;
            }
        } catch (error) {
            console.error("Error in applyFilter:", error);
        }
    }

    mount() {
        this.header.addHeaderToPage();

        // Retrieve the seriesId from the URL
        const urlParams = new URLSearchParams(window.location.search);
        const seriesId = urlParams.get('seriesId');

        // Get the existing "Create Comic Book" button
        const createComicButton = document.getElementById('create');

        // Check if the button exists
        if (createComicButton) {
            // Add a click event listener to navigate to createComicBook.html with seriesId
            createComicButton.addEventListener('click', () => {
                // Ensure that seriesId is available before navigating
                if (seriesId) {
                    // Navigate to createComicBook.html with seriesId included in the URL
                    window.location.href = `createComicBook.html?seriesId=${seriesId}`;
                } else {
                    console.error("Series ID is missing.");
                }
            });
        } else {
            console.error("Create Comic Book button not found.");
        }
    }
}

const main = async () => {
    const viewAllComicBooks = new ViewAllComicBooks();
    viewAllComicBooks.mount();
};

window.addEventListener('DOMContentLoaded', main);