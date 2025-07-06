//3606. Coupon Code Validator
import java.util.*;

public class A{

    // Approach-1:Filter + Custom Sort
    // TC = O(), SC = O()
/*
 1. Valid coupon:
    a. Coupon[i] != "" i.e. non null
    b. Coupon[i] -> a-z or A-Z or 0-9 or _ only
    c. isActive[i] = true
    d. businessLine[i] != "invalid"
 */
public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive){
    class Coupon{ //Coupons class to store valid entries
        String code;
        String businessLine;

        public Coupon(String code, String businessLine){
            this.code = code;
            this.businessLine = businessLine;
        }
    }

    int n = code.length;
    
    //Define allowed businessLine categories and their custom order
    Map<String, Integer> businessOrder = new HashMap<>();
    //lower value -> Higher priority
    businessOrder.put("electronics", 0);
    businessOrder.put("grocery", 1);
    businessOrder.put("pharmacy", 2);
    businessOrder.put("restaurant", 3);

    List<Coupon> validCoupons = new ArrayList<>();
    for(int i = 0; i<n; i++){
        if(isValid(code[i], businessLine[i], isActive[i], businessOrder)){
            validCoupons.add(new Coupon(code[i], businessLine[i]));
        }
    }

    //Sort based on businessLine order then lexographical order
    Collections.sort(validCoupons, (a,b) -> {
        int cmp = businessOrder.get(a.businessLine) - businessOrder.get(b.businessLine); //comparator
        if(cmp == 0) return a.code.compareTo(b.code);  //alphabetical sorting if they belong to same businessLine
        return cmp;
    });

    //Extract the code list
    List<String> result = new ArrayList<>();
    for(Coupon c: validCoupons) result.add(c.code);

    return result;
   }

   private boolean isValid(String code, String businessLine, boolean isActive, Map<String, Integer> businessOrder){
     if(code == null || code.isEmpty()) return false;
     if(!code.matches("[a-zA-Z0-9_]+")) return false;  
     if(!businessOrder.containsKey(businessLine)) return false;
     return isActive; 
   }

}
