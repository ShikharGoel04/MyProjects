<?php
require "init.php";
$member1=$_POST["member1"];
$member2=$_POST["member2"];
$member3=$_POST["member3"];
$member4=$_POST["member4"];
$id=$_POST["id"];
$response=array();
$sql="select groupid from registration where name like '".$member1."'";
$result=mysqli_query($con,$sql);
$row=mysqli_fetch_row($result);
$id1=$row[0];
$sql1="select groupid from registration where name like '".$member2."'";
$result1=mysqli_query($con,$sql1);
$row1=mysqli_fetch_row($result1);
$id2=$row1[0];
$sql2="select groupid from registration where name like '".$member3."'";
$result2=mysqli_query($con,$sql2);
$row2=mysqli_fetch_row($result2);
$id3=$row2[0];
$sql3="select groupid from registration where name like '".$member4."'";
$result3=mysqli_query($con,$sql3);
$row3=mysqli_fetch_row($result3);
$id4=$row3[0];
if($id1=="")
{
$id1=$id;
}
else
{
$id1.=" ".$id;
}
if($id2=="")
{
$id2=$id;
}
else
{
$id2.=" ".$id;
}
if($id3=="")
{
$id3=$id;
}
else
{
$id3.=" ".$id;
}
if($id4=="")
{
$id4=$id;
}
else
{
$id4.=" ".$id;
}
$mem="";
$mem.=" ".$member1." ".$member2." ".$member3." ".$member4;
$sql90="UPDATE diffgroups SET GroupMembers='".$mem."' where GroupID='".$id."'";
$result90=mysqli_query($con,$sql90);
$ins="Update registration SET groupid='".$id1."' where name like '".$member1."'";
$resultins=mysqli_query($con,$ins);
$response=array();
$ins1="Update registration SET groupid='".$id2."' where name like '".$member2."'";
$resultins1=mysqli_query($con,$ins1);
$ins2="Update registration SET groupid='".$id3."'where name like '".$member3."'";
$resultins2=mysqli_query($con,$ins2);
$ins3="Update registration SET groupid='".$id4."'where name like '".$member4."'";
$resultins3=mysqli_query($con,$ins3);
if($resultins && $result90)
{
$success="successfully added member";
array_push($response,array("success"=>$success));
echo json_encode($response);
}
?>