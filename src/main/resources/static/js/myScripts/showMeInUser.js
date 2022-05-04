const meUrl = "http://localhost:8080/api/users/me";

fetch(meUrl).then(
    res => {
        res.json().then(
            data => {

                document.getElementById("activeUserInfoInUser").innerHTML = `   
                    <tr>
                    <td>${data.id}</td>
                    <td>${data.first_name}</td>
                    <td>${data.last_name}</td>
                    <td>${data.age}</td>
                    <td>${data.username}</td>
                    <td>${data.rolesToString}</td>
                    </tr>
                    `;
            }
        )


    }
)

