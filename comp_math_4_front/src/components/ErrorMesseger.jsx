import React, { useEffect, useState } from "react";
import {useSelector} from "react-redux";

const ErrorMessager = () => {
  const message = useSelector((state) => state.messageReducer.message);

  return (
    <div>
        {message && <div>{message}</div>}
    </div>
  );
};

export default ErrorMessager;
