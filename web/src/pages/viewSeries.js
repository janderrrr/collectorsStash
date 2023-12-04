import CollectorStashClient from '../api/collectorStashClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class ViewSeries extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'displaySeries'], this);
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
        titleParagraph.innerText = `Title: ${seriesItem.title || 'N/A'}`;

        const volumeNumberParagraph = document.createElement('p');
        volumeNumberParagraph.innerText = `Volume Number: ${seriesItem.volumeNumber || 'N/A'}`;

        seriesElement.appendChild(titleParagraph);
        seriesElement.appendChild(volumeNumberParagraph);

        seriesContainer.appendChild(seriesElement);
    });
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