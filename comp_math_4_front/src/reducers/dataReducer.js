import {ADD_DOTS} from "../actions/type";

const initialState = {};

export default function (state = initialState, action) {
  const { type, payload } = action;

  switch (type) {
    case ADD_DOTS:
      console.log(payload);
      return { payload };
    default:
      return state;
  }
}
