<?php
require "init.php";
$name=$_POST["name"];
$sql="select name,post,id from post where name like '".$name."'";
$result=mysqli_query($con,$sql);
$response=array();
$s=mysqli_num_rows($result);
while($s)
{
$row=mysqli_fetch_row($result);
$name=$row[0];
$post=$row[1];
$id=$row[2];
array_push($response,array("name"=>$name,"post"=>$post,"id"=>$id));
$s=$s-1;
}
echo json_encode($response);
mysqli_close($con);


?>