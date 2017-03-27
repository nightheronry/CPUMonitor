#CPU Monitor 
* How much energy is cost by your CPU

CPU Monitor is the course projection on `Green Tech`. 
We expect strong linear correlations between power consumption
and CPU load like $$
                  \frac{n!}{k!(n-k)!} = {n \choose k}
                  $$. 
So we get the correlation coefficient via power meter

We use `OperatingSystemMXBean API` to get the CPU load then 
calculate energy cost by  
