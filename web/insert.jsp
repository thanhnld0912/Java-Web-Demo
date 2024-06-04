<section>
        <h1>Add New Human to System</h1>
        <form action="InsertController" method="POST">
            <label>Human ID:</label>
            <input type="text" name="humanid" required/><br><br>
            <label>Human Name:</label>
            <input type="text" name="humanname" required/><br><br>
            <label>Human Gender:</label>
            <input type="radio" name="humangender" value="Male" required/>Male
            <input type="radio" name="humangender" value="Female" required/>Female<br><br>
            <label>Human DOB:</label>
            <input type="date" name="humandob" required/><br><br>
            <label>Type ID:</label>
            <input type="text" name="typeid" required/><br><br>
            <input type="submit" value="Add" />
        </form>
</section>