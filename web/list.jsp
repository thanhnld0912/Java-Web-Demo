<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<!DOCTYPE html>
<html>
<head>
    <title>Display Data</title>
</head>
<body>
    <form method="post" action="insert.jsp">
        <table border="2">
            <thead>
                <tr>
                    <th scope="col">User ID</th>
                    <th scope="col">Name</th>
                    <th scope="col">Birthday</th>
                    <th scope="col">Gender</th>
                    <th scope="col">Type</th>
                    <th scope="col">Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    try {
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Human";
                        String username = "sa";
                        String password = "abc123";
                        String query = "SELECT * FROM Human";
                        Connection conn = DriverManager.getConnection(url, username, password);
                        Statement statement = conn.createStatement();
                        ResultSet rs = statement.executeQuery(query);

                        while (rs.next()) {
                %>
                    <tr>
                        <th scope="row"><%= rs.getInt("humanid") %></th>
                        <td><%= rs.getString("humanname") %></td>
                        <td><%= rs.getDate("humandob") %></td>
                        <td><%= rs.getBoolean("humangender") %></td>
                        <td><%= rs.getInt("typeid") %></td>
                        <td>
                            <form method="post" action="UpdateController">
                                <input type="hidden" name="humanid" value="<%= rs.getInt("humanid") %>"/>
                                <input type="submit" value="Update"/>
                            </form>
                            <form method="post" action="DeleteController">
                                <input type="hidden" name="humanid" value="<%= rs.getInt("humanid") %>"/>
                                <input type="submit" value="Delete"/>
                            </form>
                        </td>
                    </tr>
                <%
                        }
                        rs.close();
                        statement.close();
                        conn.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                %>
            </tbody>
        </table>
        <input type="submit" value="Insert">
    </form>
</body>
</html>