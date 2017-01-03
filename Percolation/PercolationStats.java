import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    
    private Percolation percs;
    private int trialNum;
    private double[] results;
    
    // Perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials)
    {   
        if ( n <= 0 || trials <= 0 )
        {
            throw new IllegalArgumentException("N-by-N Grid needs to be at least 1-by-1 and Trials > 0");
        }
        trialNum = trials;
        results = new double[trialNum];
        for(int i = 0; i < trialNum ; i++)
        {
            percs = new Percolation(n);
            double openNum = 0;
            while(percs.percolates() != true)
            {
                int j = StdRandom.uniform(1, n + 1);
                int k = StdRandom.uniform(1, n + 1);
                if(!percs.isOpen(j,k))
                {
                    percs.open(j,k);
                    ++openNum;
                }
            }
            results[i] = openNum/(n*n);
        }
    }
    
    // Sample mean of percolation threshold
    public double mean()
    {                          
        return StdStats.mean(results);
    }
    
     // Sample standard deviation of percolation threshold
    public double stddev()
    {                       
        return StdStats.stddev(results);
    }
    
    // Low  endpoint of 95% confidence interval
    public double confidenceLo()
    {                  
        return (mean() - ((1.96*stddev())/ Math.sqrt(trialNum)));
    }
    
    // High endpoint of 95% confidence interval
    public double confidenceHi()
    {                  
        return (mean() + ((1.96*stddev())/ Math.sqrt(trialNum)));
    }
    
    // Test client, parses two integer arguments in order to find number of 
    // trials and n-by-n grid to simulate. Finds percolation threshold commonly
    // used statistics.
    public static void main(String[] args)
    {        
        int ngrid = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats percstats = new PercolationStats(ngrid, trials);
        
        String confidence = percstats.confidenceLo() + ", " + percstats.confidenceHi();
        System.out.println("mean                    = " + percstats.mean());
        System.out.println("stddev                  = " + percstats.stddev());
        System.out.println("95% confidence interval = " + confidence);
    }
}
    