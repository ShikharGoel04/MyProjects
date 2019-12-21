<?php
require "init.php";
$name=$_POST["name"];
$sql="select groupid from registration where name like '".$name."'";
$result=mysqli_query($con,$sql);
$response=mysqli_fetch_row($result);
$id1=array();
$id1=$response[0];
$ar=array();
$ar=explode(" ",$id1);
$cnt=sizeof($ar);
$arr=array();
$s=0;
$t=0;
while($s<$cnt)
{

$id=$ar[$s];
$sql1="select GroupName from diffgroups where GroupID='".$id."'";
$result1=mysqli_query($con,$sql1);
$row=mysqli_fetch_row($result1);
$gname=$row[0];
array_push($arr,array("groupnames"=>$gname,"ids"=>$id));
$s=$s+1;
}
echo json_encode($arr);
?>