import CollectorStashClient from '../api/collectorStashClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class ViewAllComicBooks extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'displayComics', 'createPropertyDiv'], this);
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
                // Once the data is loaded, display the comics
                this.displayComics();
            } else {
                console.error("Comic books data is missing in the result:", result);
            }

            // Attach event listener to the "Sort by Price" button
            const sortByPriceButton = document.getElementById('sort-by-price');
            if (sortByPriceButton) {
                sortByPriceButton.addEventListener('click', async () => {
                    // Prompt the user for the desired price
                    const desiredPrice = prompt("Enter the desired price:");

                    // Check if the user entered a price
                    if (desiredPrice !== null) {
                        try {
                            // Call the API method to get comic books based on seriesId and price
                            const priceResult = await this.client.getPriceComic(seriesId, desiredPrice);

                            // Update the stored comicbooks data with the sorted data
                            this.dataStore.set('comicbooks', priceResult.comicbooks);

                            // Display the comics with the updated data
                            this.displayComics();
                        } catch (error) {
                            console.error("Error getting comic books by price:", error);
                        }
                    }
                });
            } else {
                console.error("Sort by Price button not found.");
            }
        } catch (error) {
            console.error("Error in clientLoaded:", error);
        }
    }

    displayComics() {
        console.log("displayComics called");
        const comicBooksData = this.dataStore.get('comicbooks');

        // Check if comicBooksData is defined and not null
        if (!comicBooksData) {
            console.error("Comic books data is undefined or null.");
            return;
        }

        // Sort the comic books based on issueNumber
        comicBooksData.sort((a, b) => a.issueNumber - b.issueNumber);

        const comicBooksContainer = document.getElementById('comicBooksContainer');

        comicBooksContainer.innerHTML = '';

        comicBooksData.forEach((comic) => {
            const comicElement = document.createElement('div');
            comicElement.className = 'comic-book';

            comicElement.style.display = 'grid';
            comicElement.style.gridTemplateColumns = '1fr 1fr';
            // Create a hidden div for seriesId
            const seriesIdDiv = this.createPropertyDiv('Series ID:', comic.seriesId || 'N/A');
            seriesIdDiv.style.display = 'none'; // Hide the seriesId
            const titleDiv = this.createPropertyDiv('Title:', comic.title || 'N/A');
            const issueNumberDiv = this.createPropertyDiv('Issue Number:', comic.issueNumber || 'N/A');
            const volumeNumberDiv = this.createPropertyDiv('Volume Number:', comic.volumeNumber || 'N/A');
            const yearDiv = this.createPropertyDiv('Year:', comic.year || 'N/A');
            const priceDiv = this.createPropertyDiv('Price:', comic.price || 'N/A');
            const isFavoriteDiv = this.createPropertyDiv('Is Favorite:', comic.favorite || 'N/A');
            const publisherDiv = this.createPropertyDiv('Publisher:', comic.publisher || 'N/A');

            comicElement.appendChild(seriesIdDiv);
            comicElement.appendChild(issueNumberDiv);
            comicElement.appendChild(titleDiv);
            comicElement.appendChild(volumeNumberDiv);
            comicElement.appendChild(yearDiv);
            comicElement.appendChild(priceDiv);
            comicElement.appendChild(isFavoriteDiv);
            comicElement.appendChild(publisherDiv);

            comicBooksContainer.appendChild(comicElement);
        });
    }

    // Helper method to create property and value divs
    createPropertyDiv(propertyText, valueText) {
        const propertyDiv = document.createElement('div');
        propertyDiv.className = 'property';

        const valueDiv = document.createElement('div');
        valueDiv.className = 'value';

        // Check if the value is a boolean and display 'Yes' or 'No' accordingly
        const displayValue = typeof valueText === 'boolean' ? (valueText ? 'Yes' : 'No') : valueText;

        valueDiv.textContent = `${propertyText} ${displayValue || 'N/A'}`;

        propertyDiv.appendChild(valueDiv);

        return propertyDiv;
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