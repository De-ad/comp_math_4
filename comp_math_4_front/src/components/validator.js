import ErrorMesseger from "./ErrorMesseger";

const validateDouble = (x) => {
  if (x.toString().trim() === "" || isNaN(Number(x))) {
    return false;
  }
  const value = parseFloat(x);
  return !(value < -3 || value > 3);
};
export { validateDouble };
