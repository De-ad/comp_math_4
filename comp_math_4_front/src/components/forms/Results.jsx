import React, {useEffect, useState} from "react";
import { connect, useDispatch, useSelector } from "react-redux";
import {da} from "plotly.js/src/traces/carpet/attributes";

const Results = () => {
  const data = useSelector((state) => state.dataReducer.payload);

  const [name, setName] = useState("");
  const [minim, setMinim] = useState(100000);

  useEffect(() => {
      let temp = 1000;
      let tempName = "name";
      if (data) {
          for (let i = 0; i < data.length; i++) {
              if (data[i].standardDeviation < temp) {
                  temp = data[i].standardDeviation;
                  tempName = data[i].methodName;
              }
          }
      }
      setMinim(temp);
      setName(tempName);

  }, [data])


  return data ? (
      <div className="border-2 p-2">
          <div>Best approximation: {name}, {minim}</div>
          {data.map((item) => (
              <table className='border-2 m-2'>

                  <tr className='border-2 '>{item.methodName}</tr>
                  <tr className='border-2 flex justify-between'>
                      <div> phi</div>
                      {item.fi_x.map((value) => (
                          <div className='border-2 p-2'>{value}</div>
                      ))}</tr>
                  <tr className='border-2 flex justify-between' > <div> eps </div>
                      {item.eps.map((value) => (
                          <div className='border-2 p-2'>{value}</div>
                      ))}</tr>
                  <tr className='border-2 flex justify-between' > <div> coefficients </div>
                      {item.coefficients.map((value) => (
                          <div className='border-2 p-2'>{value}</div>
                      ))}</tr>
                  <tr>standard deviation = {item.standardDeviation}</tr>


              </table>
          ))}
      </div>
  ) : (
      <div></div>
  )
};


export default Results;
