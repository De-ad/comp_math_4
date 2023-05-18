import axios from "axios";
const API_DATA_URL = "http://localhost:8082/vichka";


export const getXArray = (dotsData) => {
  let xArray = new Array(dotsData.length);
  xArray = dotsData.map((item) => (
      xArray[item.number] = parseFloat(item.x.replace(/,/g, "."))
  ));
  return xArray
}

export const getYArray = (dotsData) => {
  let yArray = new Array(dotsData.length);
  yArray = dotsData.map((item) => (
      yArray[item.number] = parseFloat(item.y.replace(/,/g, "."))
  ));
  return yArray;
}

async function sendDots(dotsData) {
  let xArray = getXArray(dotsData);
  let yArray = getYArray(dotsData);
  for (let i = 0; i < dotsData.length; i++) {
    if (isNaN(xArray[i]) || isNaN(yArray[i])) {
      alert("data is not valid");
      return;
    }
  }
  return await axios.post(API_DATA_URL + "/calculate", {
    "xArray" : xArray,
    "yArray" : yArray
  });
}



export default { sendDots, getYArray, getXArray };
