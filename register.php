<?php
require "init.php";
$name=$_POST["name"];
$surname=$_POST["surname"];
$course=$_POST["course"];
$password=$_POST["password"];
$group=$_POST["group"];
$sql1="select* from registration where name like'".$name."'";
$result23=mysqli_query($con,$sql1);
$res=mysqli_fetch_row($result23);
$response=array();
if($res)
{
$success="Error";
$message="user already exist";
array_push($response,array("success"=>$success,"message"=>$message));
echo json_encode($response);
}
else
{
$result="insert into registration (name,surname,Course,Password) VALUES ('".$name."','".$surname."','".$course."','".$password."');";
if(mysqli_query($con,$result))
{
$success="Successfully inserted";
$message="created successfully";
array_push($response,array("success"=>$success,"message"=>$message));
echo json_encode($response);
}
else
{
$success="Not successfully inserted";
$message="not successfully created";
array_push($response,array("success"=>$success,"message"=>$message));
echo json_encode($response);
}
}
?>