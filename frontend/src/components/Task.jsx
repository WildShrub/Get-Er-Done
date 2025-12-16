import { useState } from 'react'
import TaskInput from "./taskInput/taskInput"
import TaskList from "./taskList/taskList"
import "./Task.css"

function Task() {
  const [tasks, setTasks] = useState([]);
  
  const addTask = (task) => {
    if (task.trim() === "") return;

    fetch("http://localhost:8080/api/tasks", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        username: "josh",
        task: task
      })
    })
      .then(res => res.json())
      .then(data => setTasks([...tasks, data.task]))
      .catch(err => console.error(err));
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