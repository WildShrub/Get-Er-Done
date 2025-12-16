import GroupTasks from "./GroupTasks/GroupTasks"
import GroupSchedule from "./GroupSchedule/GroupSchedule"
import GroupProfile from "./GroupProfile/GroupProfile"

function Group() {
    return(
        <div>
            <h1>Group Project Page</h1>
            <GroupTasks />
            <GroupSchedule />
            <GroupProfile />
        </div>  
    )
}

export default Group