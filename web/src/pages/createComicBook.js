import CollectorStashClient from '../api/collectorStashClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class CreateComicBook extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'redirectToViewComics'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        this._seriesId = null; // Private property to store seriesId
    }

    mount() {
        console.log("Mounting CreateComicBook...");
        document.getElementById('create').addEventListener('click', this.submit);
        this.header.addHeaderToPage();
        this.client = new CollectorStashClient();

        // Retrieve the seriesId from the URL
        const urlParams = new URLSearchParams(window.location.search);
        this._seriesId = urlParams.get('seriesId');
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

            const title = document.getElementById('comic-title').value;
            const volumeNumber = document.getElementById('volume-number').value;
            const issueNumber = document.getElementById('issue-number').value;
            const year = document.getElementById('comic-year').value;
            const publisher = document.getElementById('comic-publisher').value;
            const price = document.getElementById('comic-price').value;
            const isFavorite = document.getElementById('is-favorite').value === 'true';

            console.log("Form values:", { title, volumeNumber, issueNumber, year, publisher, price, isFavorite });

            if (title.trim() === '') {
                createButton.innerText = origButtonText;
                errorMessageDisplay.innerText = 'Please enter a comic title.';
                errorMessageDisplay.classList.remove('hidden');
                return;
            }

            if (isNaN(volumeNumber) || volumeNumber <= 0) {
                createButton.innerText = origButtonText;
                errorMessageDisplay.innerText = 'Please enter a valid volume number.';
                errorMessageDisplay.classList.remove('hidden');
                return;
            }

            // Call the CollectorStashClient to create a new comic book
            const createdComicBooks = await this.client.createComicBook(
                this._seriesId, // Include the seriesId in the request
                title,
                volumeNumber,
                issueNumber,
                year,
                publisher,
                price,
                isFavorite,
            );

            this.dataStore.set('comicbook', createdComicBooks);
            console.log("Created Comic Books:", createdComicBooks);

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