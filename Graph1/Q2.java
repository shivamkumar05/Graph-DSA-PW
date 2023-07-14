import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
 
public class Q2
{
    private static void printItinerary(Map<String, String> map, String src, int flag)
    {
        String dest = map.get(src);
        if (dest == null) {
            return;
        }
      
        if(flag == 0)
            System.out.print(src + " —> " + dest);
        else 
            System.out.print(" —> " + dest);
        printItinerary(map, dest, 1);
    }
 

    private static void findItinerary(String[][] input)
    {
        Map<String, String> tickets = Stream.of(input)
                .collect(Collectors.toMap(p -> p[0], p -> p[1]));
 
        // construct a set of destination airports
        Set<String> destinations = new HashSet<>(tickets.values());
 
        for (String airport: tickets.keySet())
        {
            if (!destinations.contains(airport))
            {
                // when the source airport is found, print the itinerary
                printItinerary(tickets, airport, 0);
                return;
            }
        }
    }
 
    public static void main(String[] args)
    {
        // input: list of tickets
        String[][] input = new String[][]{
                {"LAX", "DXB"},
                {"DFW", "JFK"},
                {"LHR", "DFW"},
                {"JFK", "LAX"}
        };
 
        findItinerary(input);
    }
}