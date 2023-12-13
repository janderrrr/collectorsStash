import CollectorStashClient from '../api/collectorStashClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class ViewByPriceComicBooks extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'displayComics'], this);
        this.dataStore = new DataStore();
        this.client = new CollectorStashClient();
        this.header = new Header(this.dataStore);
        console.log("viewByPriceComicBooks constructor");

        // Call clientLoaded after the client has been initialized
        this.clientLoaded();
    }

    async clientLoaded() {
        try {
            // Extract seriesId and price from the URL
            const urlParams = new URLSearchParams(window.location.search);
            const seriesId = urlParams.get('seriesId');
            const price = parseFloat(urlParams.get('price'));

            // Check if seriesId and price are present
            if (!seriesId || isNaN(price)) {
                console.error("Series ID or price is missing or invalid in the URL.");
                return;
            }

            // Fetch comicbooks data for the specified seriesId and price
            const result = await this.client.getPriceComic(seriesId, price);
            console.log("Result in clientLoaded:", result);

            // Check if comicbooks array is present in the result
            if (result && result.comicbooks) {
                // Store comicbooks data in the DataStore
                this.dataStore.set('comicbooks', result.comicbooks);
                // Once the data is loaded, display the comics
                this.displayComics();
            } else {
                console.error("Comic books data is missing in the result:", result);
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

        // Extract the desired price from the URL
        const urlParams = new URLSearchParams(window.location.search);
        const desiredPrice = parseFloat(urlParams.get('price'));

        // Filter comic books based on the desired price
    const filteredComicBooks = comicBooksData.filter(comic => comic.price !== undefined && parseFloat(comic.price) >= desiredPrice);

        // Sort the filtered comic books based on price
        filteredComicBooks.sort((a, b) => parseFloat(a.price) - parseFloat(b.price));

        const comicBooksContainer = document.getElementById('comicBooksContainer');

        comicBooksContainer.innerHTML = '';

        filteredComicBooks.forEach((comic) => {
            const comicElement = document.createElement('div');
            comicElement.className = 'comic-book';

            // Modify the display logic as needed
            const titleDiv = this.createPropertyDiv('Title:', comic.title || 'N/A');
            const issueNumberDiv = this.createPropertyDiv('Issue Number:', comic.issueNumber || 'N/A');
            const volumeNumberDiv = this.createPropertyDiv('Volume Number:', comic.volumeNumber || 'N/A');
            const yearDiv = this.createPropertyDiv('Year:', comic.year || 'N/A');
            const priceDiv = this.createPropertyDiv('Price:', comic.price || 'N/A');
            const isFavoriteDiv = this.createPropertyDiv('Is Favorite:', comic.favorite || 'N/A');
            const publisherDiv = this.createPropertyDiv('Publisher:', comic.publisher || 'N/A');

            comicElement.appendChild(titleDiv);
            comicElement.appendChild(issueNumberDiv);
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
        const returnToViewComicsButton = document.getElementById('return-to-view-comics');

        // Check if the button exists
        if (returnToViewComicsButton) {
            // Add a click event listener to navigate back to viewAllComicBooks.html with seriesId included in the URL
            returnToViewComicsButton.addEventListener('click', () => {
                // Ensure that seriesId is available before navigating
                if (seriesId) {
                    // Navigate to viewAllComicBooks.html with seriesId included in the URL
                    window.location.href = `viewAllComicBooks.html?seriesId=${seriesId}`;
                } else {
                    console.error("Series ID is missing.");
                }
            });
        } else {
            console.error("Return to View Comics button not found.");
        }
    }
}

const main = async () => {
    const viewByPriceComicBooks = new ViewByPriceComicBooks();
    viewByPriceComicBooks.mount();
};

window.addEventListener('DOMContentLoaded', main);