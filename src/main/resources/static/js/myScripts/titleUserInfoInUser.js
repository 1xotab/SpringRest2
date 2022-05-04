fetch(meUrl).then(
    res => {
        res.json().then(
            data => {

                document.getElementById("titleInUser").innerHTML = ` 
   
                    <b>${data.username}</b>
                    <span style="font-family: sans-serif">with roles:</span>
                    <span style="font-family: sans-serif">${data.rolesToString}</span>
                    `;
            }
        )


    }
)