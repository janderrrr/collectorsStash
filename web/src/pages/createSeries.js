import CollectorStashClient from '../api/collectorStashClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class CreateSeries extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'redirectToViewSeries'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    mount() {
        document.getElementById('create').addEventListener('click', this.submit);
        this.header.addHeaderToPage();
        this.client = new CollectorStashClient();
    }

    async submit(event) {
        event.preventDefault();
        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = '';
        errorMessageDisplay.classList.add('hidden');
        const createButton = document.getElementById('create');
        const origButtonText = createButton.innerText;
        createButton.innerText = 'Updating...';

        const seriesTitle = document.getElementById('series-title').value;
        const volumeNumber = document.getElementById('volume-number').value;

        if (seriesTitle.trim() === '') {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = 'Please enter a series title.';
            errorMessageDisplay.classList.remove('hidden');
            return;
        }

        if (isNaN(volumeNumber) || volumeNumber <= 0) {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = 'Please enter a valid volume number.';
            errorMessageDisplay.classList.remove('hidden');
            return;
        }

        try {
            // Create the series and retrieve the updated series data
            const series = await this.client.createSeries(seriesTitle, volumeNumber);

            // Update the series data in the data store
            this.dataStore.set('series', series);

            // Redirect to the view series page
            this.redirectToViewSeries();
        } catch (error) {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        }
    }

    redirectToViewSeries() {
        // Redirect to the view series page
        window.location.href = '/viewSeries.html';
    }
}

const main = async () => {
    const createSeries = new CreateSeries();
    createSeries.mount();
};

window.addEventListener('DOMContentLoaded', main);