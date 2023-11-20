import VendorEventClient from '../api/vendorEventClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the view Vendors from Event page of the website.
 */

class ViewVendorsForEvent extends BindingClass {

    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'displayVendorsForEvent'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        this.client = new VendorEventClient();

        console.log("ViewVendorsForEvent constructor");
    }

    //Mount method
        mount() {
            this.header.addHeaderToPage();
            this.clientLoaded();
            console.log("mount");
        }

    async clientLoaded() {
        this.showLoading();

        const urlParams = new URLSearchParams(window.location.search);
        const id = urlParams.get('id');
        const date = urlParams.get('date');
        const result = await this.client.viewVendorsForEvent(id, date);
        this.hideLoading();

        console.log("Result:", result);
        this.dataStore.set('vendors', result);

        this.displayVendorsForEvent();

    }
    showLoading() {
        document.getElementById('vendor-loading').innerText = "(Loading Event...)";
    }

    hideLoading() {
        document.getElementById('vendor-loading').style.display = 'none';
    }

    displayVendorsForEvent() {
        const vendors = this.dataStore.get('vendors');
        const displayDiv = document.getElementById('vendor-loading');

        console.log(vendors);


    }
}
    const main = async () => {
        const viewVendorsForEvent = new ViewVendorsForEvent();
        viewVendorsForEvent.mount();
    };

window.addEventListener('DOMContentLoaded', main);