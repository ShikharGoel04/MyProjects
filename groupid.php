<?php
require "init.php";
$group=$_POST["groupss"];
$sql="insert into diffgroups (GroupName) VALUES ('".$group."');";
$result=mysqli_query($con,$sql);
$response=array();
$sql1="select GroupID from diffgroups Order By GroupID DESC LIMIT 1";
$result1=mysqli_query($con,$sql1);
if($result)
{
$success="Group is created";
$row=mysqli_fetch_row($result1);
$id=$row[0];
array_push($response,array("success"=>$success,"id"=>$id));
echo json_encode($response);
}
?>