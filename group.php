<?php
require "init.php";
$member1=$_POST["member1"];
$member2=$_POST["member2"];
$member3=$_POST["member3"];
$member4=$_POST["member4"];
$group=$_POST["groupss"];
$sql="select surname from registration where name like '".$member1."'";
$result=mysqli_query($con,$sql);
$sql1="select surname  from registration where name like '".$member2."'";
$result1=mysqli_query($con,$sql1);
$sql2="select surname from registration where name like '".$member3."'";
$result2=mysqli_query($con,$sql2);
$sql3="select surname from registration where name like '".$member4."'";
$result3=mysqli_query($con,$sql3);
$response=array();
$s=mysqli_num_rows($result);
$u=mysqli_num_rows($result1);
$v=mysqli_num_rows($result2);
$w=mysqli_num_rows($result3);
$z=0;
if((!$s)||(!$u)||(!$v)||(!$w))
{
$z=1;
$success="Member don't exist";
array_push($response,array("success"=>$success));
echo json_encode($response);
}
$sql4="select name from registration where groups like '".$group."'";
$result4=mysqli_query($con,$sql4);
$t=mysqli_num_rows($result4);
if($t>0)
{
$success="Group name already exist";
array_push($response,array("success"=>$success));
echo json_encode($response);
}
if($z==0)
{
$sql5="Update registration SET groups='".$group."' where (name like '".$member1."' OR name like '".$member2."' OR name like '".$member3."' OR name like '".$member4."');";
$result5=mysqli_query($con,$sql5);
if($result5)
{
$success="Group is successfully created";
array_push($response,array("success"=>$success));
echo json_encode($response);
}
}
?>