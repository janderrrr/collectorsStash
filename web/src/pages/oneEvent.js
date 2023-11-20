import VendorEventClient from '../api/vendorEventClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the view all Events page of the website.
 */
class OneEvent extends BindingClass {
    //Constructor
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'displayEvent', 'showLoading', 'hideLoading', 'viewVendorsForEvent'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
//        this.dataStore.addChangeListener(this.displayVendors);
        this.client = new VendorEventClient();

        console.log("oneEvent constructor");
    }

    //Mount method to initialize the page
        mount() {
            this.header.addHeaderToPage();
            this.clientLoaded();
            document.getElementById('showVendors').addEventListener('click', this.viewVendorsForEvent);
        }

    /**
    * Once the client is loaded, get the event metadata and event list.
    */
    async clientLoaded() {
        this.showLoading();

        const urlParams = new URLSearchParams(window.location.search);
        const id = urlParams.get('id');
        const date = urlParams.get('date');
        const result = await this.client.getOneEvent(id, date);
        this.hideLoading();

        console.log("Result:", result);
        this.dataStore.set('event', result);

        this.displayEvent();
    }

    showLoading() {
        document.getElementById('event-loading').innerText = "(Loading Event...)";
    }

    hideLoading() {
        document.getElementById('event-loading').style.display = 'none';
    }

    async viewVendorsForEvent() {
        const event = this.dataStore.get('event');
        const eventId = encodeURIComponent(event.id);
        const date = encodeURIComponent(event.date);

        const currentHostname = window.location.hostname;

        const isLocal = currentHostname === 'localhost' || currentHostname === '127.0.0.1';
        const baseUrl = isLocal ? 'http://localhost:8000/' : 'https://d3hqn9u6ae71hc.cloudfront.net/';

        const VendorsPageUrl = `${baseUrl}viewVendorsForEvent.html?id=${eventId}&date=${date}`;

        window.location.href = VendorsPageUrl;

    }

    //display Event for the one event
    displayEvent() {
        const event = this.dataStore.get('event');
        const displayDiv = document.getElementById('event-list-display');
        const displayName = document.getElementById('display-name');

        const eventName = document.createElement('h1');
        eventName.innerText = event.name;

        const detailsCard = document.createElement('section');
        detailsCard.className = 'card';

        const eventPrice = document.createElement('h3');
        eventPrice.innerText = "Price: "+ "$"+event.price;

        const eventLocation = document.createElement('h3');
        eventLocation.innerText = "Location: "+ event.location;

//        const eventVendors = document.createElement('h3');
//        eventVendors.innerText = "Vendors: " + event.vendors;

        const eventDate = document.createElement('h3');
        eventDate.innerText = formatDate(event.date);

        const tagsList = createTagList(event.tags);
//        const eventTag = document.createElement('h3');
//        eventTag.innerText = event.tags.join(', ');

        displayName.appendChild(eventName);
        detailsCard.appendChild(eventPrice);
        detailsCard.appendChild(eventLocation);
        detailsCard.appendChild(eventDate);
        detailsCard.appendChild(tagsList);
//        detailsCard.appendChild(eventVendors);
//        detailsCard.appendChild(eventTag);
        displayDiv.appendChild(detailsCard)
    }
}
    function formatDate(dateStr) {
        const dateObj = new Date(dateStr);
        return dateObj.toLocaleString('en-US', {
            month: 'long',
            day: 'numeric',
            year: 'numeric',
            hour: '2-digit',
            hour12: true
        });
    }
function createTagList(tags) {
    const ul = document.createElement('ul');
    ul.className = 'tagList';
    tags.forEach(tag => {
        const li = document.createElement('li');
        li.innerText = tag.charAt(0).toUpperCase() + tag.slice(1).trim();
        ul.appendChild(li);
    });
    return ul;
}
    const main = async () => {
        const oneEvent = new OneEvent();
        oneEvent.mount();
    };

window.addEventListener('DOMContentLoaded', main);