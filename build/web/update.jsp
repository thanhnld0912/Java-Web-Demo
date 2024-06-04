<section>
        <h1>Update Human</h1>
        <form action="UpdateHandler" method="POST">
            <input type="hidden" name="humanid" value="<%= request.getAttribute("humanid") %>"/>
            <label>Human Name:</label>
            <input type="text" name="humanname" value="<%= request.getAttribute("humanname") %>" required/><br><br>
            <label>Human Gender:</label>
            <input type="radio" name="humangender" value="Male" <%= (Boolean)request.getAttribute("humangender") ? "checked" : "" %> required/>Male
            <input type="radio" name="humangender" value="Female" <%= !(Boolean)request.getAttribute("humangender") ? "checked" : "" %> required/>Female<br><br>
            <label>Human DOB:</label>
            <input type="date" name="humandob" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(request.getAttribute("humandob")) %>" required/><br><br>
            <label>Type ID:</label>
            <input type="text" name="typeid" value="<%= request.getAttribute("typeid") %>" required/><br><br>
            <input type="submit" value="Update"/>
        </form>
    </section>