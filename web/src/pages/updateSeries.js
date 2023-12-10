import CollectorStashClient from '../api/collectorStashClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class UpdateSeries extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'redirectToViewSeries'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    mount() {
        document.getElementById('update').addEventListener('click', this.submit);
        this.header.addHeaderToPage();
        this.client = new CollectorStashClient();
    }

    async submit(event) {
        event.preventDefault();
        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = '';
        errorMessageDisplay.classList.add('hidden');
        const updateButton = document.getElementById('update');
        const origButtonText = updateButton.innerText;
        updateButton.innerText = 'Loading...';

        // Extract seriesId from the URL parameters
        const urlParams = new URLSearchParams(window.location.search);
        const seriesId = urlParams.get('seriesId');

        const seriesTitle = document.getElementById('series-title').value;
        const volumeNumber = document.getElementById('volume-number').value;

        console.log('Updating series with ID:', seriesId);
        console.log('New title:', seriesTitle);
        console.log('New volume number:', volumeNumber);

        if (seriesTitle.trim() === '' || isNaN(volumeNumber) || volumeNumber <= 0) {
            updateButton.innerText = origButtonText;
            errorMessageDisplay.innerText = 'Invalid input. Please check your values.';
            errorMessageDisplay.classList.remove('hidden');
            return;
        }

        try {
            // Update the series and retrieve the updated series data
            const series = await this.client.updateSeries(seriesId, seriesTitle, volumeNumber);

            // Update the series data in the data store
            this.dataStore.set('series', series);

            // Redirect to the view series page or perform any other necessary actions
            this.redirectToViewSeries();

        } catch (error) {
            console.error('Error updating series:', error);
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');

        } finally {
            // Ensure that the loading spinner stops
            updateButton.innerText = origButtonText;
        }
    }

    redirectToViewSeries() {
        // Redirect to the view series page
        window.location.href = '/viewSeries.html';
    }
}

const main = async () => {
    const updateSeries = new UpdateSeries();
    updateSeries.mount();
};

window.addEventListener('DOMContentLoaded', main);



