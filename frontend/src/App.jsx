
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Task from "./components/Task"
import Layout from "./components/Layout"
import Login from "./components/Login/Login"
import Timeblock from "./components/TimeBlock/Timeblock";
import Group from "./components/Groups/Group";
import Notification from "./components/Notification/NotificationQueue";
import UserProfile from "./components/Profile/UserProfile";

import './App.css'

function App() {
  
  return(
    <Router>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Login/>}></Route>
          <Route path="Notification" element={<Notification />}></Route>
          <Route path="Todo" element={<Task/>}></Route>
          <Route path="Timeblock" element={<Timeblock/>}></Route>
          <Route path="Group" element={<Group/>}></Route>
          <Route path="Profile" element={<UserProfile/>}></Route>
        </Route>
      </Routes>
    </Router>
  )

}

export default App