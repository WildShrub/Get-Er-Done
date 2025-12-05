import { Link } from "react-router-dom"
import "./Navbar.css"

function Navbar() {
    return (
        <div className="Navbar">
            <ul className="nav-links">
                <li>
                    <Link className="login-button" to="/">Login</Link>
                </li>
                <li>
                    <Link className="todo-button" to={"/Todo"}>Todo List</Link>
                </li>
                <li>
                    <Link className="timeblock-button" to={"/Timeblock"}>Time Block</Link>
                </li>
            </ul>
        </div>
    )
}

export default Navbar