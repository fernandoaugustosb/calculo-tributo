/* 
 * The MIT License
 *
 * Copyright 2017 Chronusinfo.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.chronos.calc.csosn;

import com.chronos.calc.dto.ITributavel;
import com.chronos.calc.enuns.Csosn;
import com.chronos.calc.enuns.OrigemMercadoria;
import java.math.BigDecimal;

/**
 * ICMS cobrado anteriormente por substituição tributária (substituído) ou por
 * antecipação - Classificam-se neste código as operações sujeitas
 * exclusivamente ao regime de substituição tributária na condição de
 * substituído tributário ou no caso de antecipações
 *
 * @author John Vanderson M L
 */
public class Csosn500 extends CsosnBase {

    private BigDecimal percentualBcStRetido;
    private BigDecimal valorBcStRetido;

    public Csosn500() {
        this.geraDifal = true;
        this.csosn = Csosn.Csosn500;
    }

    public Csosn500(OrigemMercadoria origemMercadoria) {
        super(origemMercadoria);
        this.geraDifal = true;
        this.csosn = Csosn.Csosn500;
    }

    @Override
    public void calcular(ITributavel tributos) {

    }

    public BigDecimal getPercentualBcStRetido() {
        return percentualBcStRetido;
    }

    public void setPercentualBcStRetido(BigDecimal percentualBcStRetido) {
        this.percentualBcStRetido = percentualBcStRetido;
    }

    public BigDecimal getValorBcStRetido() {
        return valorBcStRetido;
    }

    public void setValorBcStRetido(BigDecimal valorBcStRetido) {
        this.valorBcStRetido = valorBcStRetido;
    }

}
