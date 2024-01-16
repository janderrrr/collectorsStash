import CollectorStashClient from '../api/collectorStashClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class CreateComicBook extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'fetchSeriesDetails'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        this._seriesId = null; // Private property to store seriesId
    }

    async mount() {
        console.log("Mounting CreateComicBook...");
        this.header.addHeaderToPage();
        this.client = new CollectorStashClient();

        // Retrieve the seriesId from the URL
        const urlParams = new URLSearchParams(window.location.search);
        this._seriesId = urlParams.get('seriesId');

        // Fetch series details and set default values for Title and Volume Number
        await this.fetchSeriesDetails();

        // Add click event listener to the 'Create' button
        document.getElementById('create').addEventListener('click', this.submit);
    }

    async fetchSeriesDetails() {
        try {
            if (this._seriesId) {
                // Fetch series details based on the seriesId
                const result = await this.client.getSeries(this._seriesId);
                console.log("Series details result:", result);

                // Check if the result has series details
                if (result && result.series && result.series.length > 0) {
                    const series = result.series.find(s => s.seriesId === this._seriesId);

                    // Check if the series with the desired seriesId is found
                    if (series) {
                        // Set the default values for Title and Volume Number
                        document.getElementById('comic-title').value = series.title || '';
                        document.getElementById('volume-number').value = series.volumeNumber || '';
                    } else {
                        console.error("Series with the specified seriesId not found:", result);
                    }
                } else {
                    console.error("Series details not found or empty:", result);
                }
            } else {
                console.error("Series ID is missing in the URL.");
            }
        } catch (error) {
            console.error("Error fetching series details:", error);
        }
    }

    async submit(event) {
        try {
            console.log("Submitting form...");
            event.preventDefault();

            const errorMessageDisplay = document.getElementById('error-message');
            errorMessageDisplay.innerText = '';
            errorMessageDisplay.classList.add('hidden');

            const createButton = document.getElementById('create');
            const origButtonText = createButton.innerText;
            createButton.innerText = 'Updating...';

            // Retrieve form field values
            const title = document.getElementById('comic-title').value;
            const volumeNumber = document.getElementById('volume-number').value;
            const issueNumber = document.getElementById('issue-number').value;
            const year = document.getElementById('comic-year').value;
            const publisher = document.getElementById('comic-publisher').value;
            const price = document.getElementById('comic-price').value;
            const isFavorite = document.getElementById('is-favorite').value === 'true';

            console.log("Form values:", { title, volumeNumber, issueNumber, year, publisher, price, isFavorite });

            // Validate form fields if needed

            // Call the AWS SDK or client method to create a new comic book
            // Replace the following line with your actual AWS SDK or client code
            // const createdComicBooks = await yourAWSClient.createComicBook(/* parameters */);

            // Example:
             const createdComicBooks = await this.client.createComicBook(
                 this._seriesId,
                 title,
                 volumeNumber,
                 issueNumber,
                 year,
                 publisher,
                 price,
                 isFavorite
             );

            // Handle the response if needed
             this.dataStore.set('comicbook', createdComicBooks);

            // Redirect to the view comics page after successful creation
            this.redirectToViewComics();
        } catch (error) {
            console.error("Error submitting form:", error);
            // Handle the error as needed
        }
    }

    redirectToViewComics() {
        console.log("Redirecting to viewAllComicBooks.html...");
        window.location.href = `/viewAllComicBooks.html?seriesId=${this._seriesId}`;
    }
}

const main = () => {
    console.log("Initializing CreateComicBook...");
    const createComicBook = new CreateComicBook();
    createComicBook.mount();
};

window.addEventListener('DOMContentLoaded', main);