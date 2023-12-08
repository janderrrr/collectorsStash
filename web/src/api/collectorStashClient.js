import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

/**
 * Client to call the CollectorsStash.
 *
  */

export default class CollectorsStash extends BindingClass {
    constructor(props = {}) {
        super();

        const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout', 'createSeries', 'getSeries'];
        this.bindClassMethods(methodsToBind, this);

        this.authenticator = new Authenticator();;
        this.props = props;

        axios.defaults.baseURL = process.env.API_BASE_URL;
        this.axiosClient = axios;
        this.clientLoaded();
    }
    clientLoaded() {
        if(this.props.hasOwnProperty("onReady")) {
            this.props.onReady(this);
        }
    }

    async getIdentity(errorCallback) {
        try {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();

            if(!isLoggedIn) {
                return undefined;
            }

            return await this.authenticator.getCurrentUserInfo();
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async login() {
        this.authenticator.login();
    }

    async logout() {
        this.authenticator.logout();
    }

    async getTokenOrThrow(unauthenticatedErrorMessage) {
        const isLoggedIn = await this.authenticator.isUserLoggedIn();
        if(!isLoggedIn) {
            throw new Error(unauthenticatedErrorMessage);
        }

        return await this.authenticator.getUserToken();
    }

    async createSeries(title, volumeNumber, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create series.");
            const response = await this.axiosClient.post(`series`, {
                title,
                volumeNumber,
            }, {
                headers: {
                    Authorization:  `Bearer ${token}`
                }
            });
            return response.data.series;
            } catch (error) {
                this.handleError(error, errorCallback);
            }
        }


    async getSeries(errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can get series.");

            const response = await this.axiosClient.get('/series', {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return {
                series: response.data.seriesList
            };
        } catch (error) {
            this.handleError(error, errorCallback);
        }
    }
async removeSeries(seriesId, customerId, errorCallback) {
    try {
        const token = await this.getTokenOrThrow("Only authenticated users can remove series.");

        const response = await this.axiosClient.delete(`/series/${seriesId}`, {
            headers: {
                Authorization: `Bearer ${token}`
            },
            data: {
                customerId: customerId
            }
        });

        if (response.status === 200) {
            return { success: true, message: "Series removed successfully" };
        } else {
            return { success: false, error: "Unknown error" };
        }
    } catch (error) {
        this.handleError(error, errorCallback);
        throw error;
    }
}


    /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(error, errorCallback) {
        console.error(error);
        const errorFromApi = error?.response?.data?.error_message;
        if (errorFromApi) {
            console.error(errorFromApi)
            error.message = errorFromApi;
        }
        if (errorCallback) {
            errorCallback(error);
        }
    }
}