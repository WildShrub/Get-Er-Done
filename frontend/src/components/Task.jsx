import { useState } from 'react'
import TaskInput from "./taskInput/taskInput"
import TaskList from "./taskList/taskList"
import "./Task.css"

function Task() {
  const [tasks, setTasks] = useState([]);
  
  const addTask = (task) => {
    if(task.trim() === "") return;
    fetch("http://localhost:8080/api/hello")
      .then(res => res.text())
      .then(data => setTasks([...tasks, data]));
    //setTasks ([...tasks, task]);
  };

  const removeTask = (index) => {
    setTasks(tasks.filter((_, i) => i !== index));
  };


  return(
    <div className="Task">
        <div style={{ textAlign: "center", marginTop: "50px" }}>
            <h1>To Do List</h1>
            <TaskInput onAddTask={addTask}/>
            <TaskList tasks={tasks} onRemoveTask={removeTask}/>
        </div>
    </div>
  )
}

export default Task