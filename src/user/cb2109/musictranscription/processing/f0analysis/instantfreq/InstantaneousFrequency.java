package user.cb2109.musictranscription.processing.f0analysis.instantfreq;

import org.apache.commons.math.complex.Complex;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Christopher Bates
 * Date: 09/03/14
 */
public class InstantaneousFrequency {

    public List<Double> calculate(List<Complex> frequencies) {
        ArrayList<Double> output = new ArrayList<Double>();
        Complex prev = null;
        Complex cur = null;
        Complex next;
        for(Complex freq : frequencies) {
            next = freq;


            if(cur != null) {
                double dbdt = differentiate(
                        prev != null ? prev.getImaginary() : null,
                        cur.getImaginary(),
                        next.getImaginary());
                double dadt = differentiate(prev != null ? prev.getReal() : null,
                        cur.getReal(),
                        next.getReal());

                output.add(calcInstFreq(cur.getReal(), cur.getImaginary(), dadt, dbdt));
            }

            prev = cur;
            cur = next;
        }
        // there is one more calculation at the end because we run out of next but cur is still not null
        if(cur != null && prev != null) {
            double dbdt = differentiate(prev.getImaginary(),
                    cur.getImaginary(),
                    null);
            double dadt = differentiate(prev.getReal(),
                    cur.getReal(),
                    null);

            output.add(calcInstFreq(cur.getReal(), cur.getImaginary(), dadt, dbdt));
        }

        return output;
    }
    // equation = (a * dbdt - b * dadt) / (a^2 + b^2)
    private double calcInstFreq(double a, double b, double dadt, double dbdt) {
        double numerator = a * dbdt - b * dadt;
        double denominator = Math.pow(a, 2) + Math.pow(b, 2);
        return numerator / denominator;
    }

    private double differentiate(Double prev, Double cur, Double next) {
        double change = 0;
        if(prev != null) {
            change += cur - prev;
        }
        if(next != null) {
            change += next - cur;
        }
        if(next != null && prev != null) {
            change /= 2;
        }
        return change;
    }

}
