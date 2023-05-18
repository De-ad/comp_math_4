import { applyMiddleware, combineReducers, createStore } from "redux";
import thunk from "redux-thunk";
import { composeWithDevTools } from "@redux-devtools/extension";
import dataReducer from "../reducers/dataReducer";
import messageReducer from "../reducers/messageReducer";

const rootReducer = combineReducers({
  dataReducer,
  messageReducer,
});

export const store = createStore(
  rootReducer,
  composeWithDevTools(applyMiddleware(thunk))
);
