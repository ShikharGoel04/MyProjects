<?php
require "init.php";
$name=$_POST["name"];
$post=$_POST["post"];
$id=$_POST["id"];
$sql="insert into post (name,post,idd) VALUES ('".$name."','".$post."','".$id."');";
$result=mysqli_query($con,$sql);
$response=array();
if($result)
{
$success="Success";
$message="Successfully posted";
array_push($response,array("success"=>$success,"message"=>$message));
echo json_encode($response);
}
else
{
$success="Failed";
$message="Some error occured";
array_push($response,array("success"=>$success,"message"=>$message));
echo json_encode($response);
}
?>