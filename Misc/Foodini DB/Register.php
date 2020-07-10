<?php
    $con = mysqli_connect("localhost", "id13740682_omar_fendri", "ZXu(@88xA>J)lA5V", "id13740682_foodini");
    
    $name = $_POST["name"];
    $age = $_POST["age"];
    $username = $_POST["username"];
    $password = $_POST["password"];

    $statement = mysqli_prepare($con, "INSERT INTO user (email, name, password) VALUES (?, ?, ?)");
    mysqli_stmt_bind_param($statement, "siss", $email, $name, $password);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>