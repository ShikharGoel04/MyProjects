<?php
require "./init.php";
$name=$_POST["name"];
$password=$_POST["password"];
$response=array();
$sql="select surname,course from registration where name like'".$name."' and Password like'".$password."'";
$result=mysqli_query($con,$sql);
if(mysqli_num_rows($result)>0)
{
$row=mysqli_fetch_row($result);

$surname=$row[0];
$course=$row[1];
$code="login success";
array_push($response,array("surname"=>$surname,"course"=>$course,"code"=>$code));
echo json_encode($response);
}
else
{
$code="login failed";
$message="User not found,Please register";
array_push($response,array("code"=>$code,"message"=>$message));
echo json_encode($response);
} 

mysqli_close($con);



?>




