import { useState } from 'react'

function TaskInput({ onAddTask }) {
    const [input, setInput] = useState('');

    const  handleSubmit = () => {
        onAddTask(input);
        setInput("");
    };

    return(
        <div style={{marginBottom: "20px"}}>

            <input type="text" value={input}
            onChange={(e) => setInput(e.target.value)}
            placeholder='Enter a Task'/>

            <button onClick={handleSubmit}>Add Task</button>
        </div>
    );
}

export default TaskInput;