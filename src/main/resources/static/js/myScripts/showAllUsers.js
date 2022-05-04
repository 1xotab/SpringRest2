const url = "http://localhost:8080/api/users/";

fetch(url).then(
    res => {
        res.json().then(
            data => {

                 console.log(data);

                if (data.length > 0) {
                    let temp = "";

                    data.forEach((u) => {

                        temp += `   
                    <tr>
                    <td>${u.id}</td>
                    <td>${u.first_name}</td>
                    <td>${u.last_name}</td>
                    <td>${u.age}</td>
                    <td>${u.username}</td>
                    <td>${u.rolesToString}</td>
                    <td><button class="btn btn-info" id="editButton">Edit</button></td>
                    <td><button class="btn btn-danger" id="deleteButton">Delete</button></td>                 
                    </tr>
                    `
                    })

                    document.getElementById("data").innerHTML = temp;
                }
            }
        )
    }
)













