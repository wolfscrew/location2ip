# location2ip

**location2ip** Get ip address subnet from IP2LOCATION file. For search name of location please use file country.lst


### Usage: 

```location2ip -i IP2LOCATION-LITE-DB1.CSV -c country```

### Sample:

```
$ cat country.lst | grep "VU"

"VU","Vanuatu"

$ ~/Library/jdk-12/bin/java -Dfile.encoding=UTF-8 -classpath /home/c0re/IdeaProjects/location2ip/out/production/location2ip Main -i c -c VU

File: IP2LOCATION-LITE-DB1.CSV

Country: VU

--------- CUT HERE ---------

43.240.140.0/22

57.71.80.0/20

103.7.197.0/24

103.16.15.0/24

103.20.232.0/23

......

--------- CUT HERE ---------
```
