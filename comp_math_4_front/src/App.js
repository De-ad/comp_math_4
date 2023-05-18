import DotsForm from "./components/forms/DotsForm";
import ErrorMessager from "./components/ErrorMesseger";

function App() {
  return (
    <div className="flex justify-center space-x-7">
      <div className="grid space-y-5 m-10">
          {/*<img src="./copium-cat.gif"/>*/}
        <DotsForm />
          <ErrorMessager/>
      </div>
    </div>
  );
}

export default App;
