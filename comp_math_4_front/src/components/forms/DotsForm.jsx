import React, {useEffect, useState} from "react";
import { useDispatch, useSelector } from "react-redux";
import { sendDots } from "../../actions/data";
import Plot from "react-plotly.js";
import Results from "./Results";
import {useLayoutEffect} from "react";
import {getXArray, getYArray} from "../../services/dataService";

const DotsForm = () => {
  const dispatch = useDispatch();
  const results = useSelector((state) => state.dataReducer.payload);
  const [dotsData, setDotsData] = useState([]);
  const [xArray, setXArray] = useState([]);
  const [yArray, setYArray] = useState([]);
  const [graph, setGraph] = useState([]);

useEffect(() => {
  if (results){
    let array = results.map((item) => (
        {x : xArray,
          y : item.fi_x,
          name : item.methodName
        }
    ))
    const newGraph =
        [
          {
            x: xArray,
            y: yArray,
            name : "function"
          },
            ...array,
        ]

    setGraph(newGraph);
  }
}, [results])

  useLayoutEffect(() =>{
    const newObjects = [];
    for(let i = 1; i <= 8 ; i++){
      const obj = {
        number : i,
        x : '',
        y : ''
      };
      newObjects.push(obj);
    }
    setDotsData(newObjects);
  },[]);

const handleFieldAdd = (e) => {
  e.preventDefault();
  const values = [...dotsData];
  if (dotsData.length < 12){
    values.push({
      number : dotsData.length + 1,
      x : "",
      y : ""
    });
    setDotsData(values);
    return;
  }
  alert("max 12 dots");
}

  const handleFileChange = (e) => {
    const newObjects = [];
    const reader = new FileReader();
    reader.readAsText(e.target.files[0]);
    reader.onload = () => {
      const lines = reader.result.split("\n");
      let dotsArray = new Array(lines.length);
       dotsArray = lines.map((item) => (item.split(","
      )))
      for (let i = 1; i <= dotsArray.length ; i++){
        const obj = {
          number : i,
          x : dotsArray[i - 1][0],
          y : dotsArray[i - 1][1]
        };
        newObjects.push(obj)
      }
    };
    setDotsData(newObjects);
  };

  const handleWriteToFile = () => {
    const content = results;
    const headers = ["chord method", "newton method", "iterations method"];
    const contentWithHeaders = content.map((obj, index) => {
      return { [headers[index]]: obj };
    });
    const fileName = "data.txt";
    const element = document.createElement("a");
    const file = new Blob([JSON.stringify(contentWithHeaders)], {
      type: "text/plain",
    });
    element.href = URL.createObjectURL(file);
    element.download = fileName;
    document.body.appendChild(element);
    element.click();
  };

  const handleDotsSend = (e) => {
    e.preventDefault();
    setXArray(getXArray(dotsData));
    setYArray(getYArray(dotsData));

    dispatch(sendDots(dotsData));

  };

  const onChangeX = (e, number) => {
    const editData = dotsData.map((item) => (
      item.number === number ? {...item, x: e.target.value} : item
    ))
    setDotsData(editData);
  }

  const onChangeY = (e, number) => {
    const editData = dotsData.map((item) => (
        item.number === number ? {...item, y: e.target.value} : item
    ))
    setDotsData(editData);
  }

  return (
    <div className="grid" >
      <div className="flex border-2 w-fit p-2 rounded-lg space-y-2">
      <div>
        <div>
          <table>
            <thead>
            <tr>
              <th> № </th>
              <th> X </th>
              <th> Y </th>
            </tr>
            </thead>
            <tbody>
              {dotsData.map(({number, x , y}) => (
                <tr>
                <td>{number}</td>
                  <td>
                    <input
                    value={x}
                    type="text"
                    onChange={(e) => onChangeX(e, number)}
                    placeholder="type x value"/>
                  </td>
                  <td>
                    <input
                        type="text"
                        placeholder="type y value"
                        onChange={(e) => onChangeY(e, number)}
                    value={y}/>
                  </td>
                </tr>
              ))}

            </tbody>
          </table>
          <div className="border-2 w-fit px-2 rounded-lg">
          <button onClick={handleFieldAdd}> add field</button>
          </div>
        </div>


        <div className="border-2 w-fit px-2 rounded-lg">
          <button onClick={handleDotsSend}>Send</button>
        </div>
        <div>or choose file with data:</div>
        <div>
          <label htmlFor="file1"> File with data : </label>
          <input
            id="file1"
            type="file"
            accept=".txt"
            onChange={handleFileChange}
          />
        </div>
        <div className="border-2 w-fit px-2 rounded-lg">
          <button onClick={handleDotsSend}>Send</button>
        </div>
        {/*<div>and you can choose file to write to:</div>*/}
        {/*<div className="border-2 w-fit px-2 rounded-lg">*/}
        {/*  <button onClick={handleWriteToFile}>Download file</button>*/}
        {/*</div>*/}
      </div>


      </div>
      <div>
        <Plot
            data={graph}
            layout={{width: 1200, height: 700}}
        />
      </div>
      <div>
         <Results/>
      </div>
    </div>
  );
};

export default DotsForm;
