import CollectorStashClient from '../api/collectorStashClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the view upcoming Events and feature vendors of the website index page.
 */

 class Index extends BindingClass {
    // Constructor
    constructor() {
        super();
        this.bindClassMethods(['mount'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        console.log("HomePage constructor");
    }


    mount() {
        this.header.addHeaderToPage();
        this.client = new CollectorStashClient();
    }
 }

/**
 * Main method to run when the page contents have loaded.
 */
 const main = async () => {
     const index = new Index();
     index.mount();
 };

 window.addEventListener('DOMContentLoaded', main);