import dataService from "../services/dataService";
import {ADD_DOTS, SET_MESSAGE} from "./type";

export const sendDots =
  (dotsData) => (dispatch) => {
    return dataService
      .sendDots(dotsData)
      .then(
        (response) => {
          dispatch({
            type: ADD_DOTS,
            payload: response.data,
          });
          return Promise.resolve();
        },
        (error) => {
          const message =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();
          dispatch({
            type: SET_MESSAGE,
            payload: { message },
          });
          return Promise.reject();
        }
      );

};
