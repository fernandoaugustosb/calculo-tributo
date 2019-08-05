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
package com.chronos.calc;

import com.chronos.calc.calculos.base.CalcularBaseCalculoBase;
import com.chronos.calc.dto.ITributavel;
import com.chronos.calc.enuns.TipoDesconto;
import com.chronos.calc.util.Biblioteca;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

/**
 *
 * @author John Vanderson M L
 */
public class CalculoBaseIcmsSt extends CalcularBaseCalculoBase {

    public CalculoBaseIcmsSt(ITributavel tributos, TipoDesconto desconto) {
        super(tributos, desconto);
    }

    @Override
    public BigDecimal getBaseCalculo() {
        BigDecimal baseCalculo = super.getBaseCalculo().add(Optional.ofNullable(getTributos().getValorIpi()).orElse(BigDecimal.ZERO));
        baseCalculo = baseCalculo.subtract(baseCalculo.multiply(getTributos().getPercentualReducao()).divide(BigDecimal.valueOf(100)));
        baseCalculo = calcularBaseCalcST(baseCalculo);
        return baseCalculo;
    }

    public BigDecimal calcularBaseCalcST(BigDecimal baseCalculoIcms) {

        BigDecimal baseCalcST = getDesconto() == TipoDesconto.Condicional
                ? calculaIcmsComDescontoCondicional(baseCalculoIcms)
                : calculaIcmsComDescontoIncondicional(baseCalculoIcms);
        baseCalcST = baseCalcST.multiply(BigDecimal.ONE.add(getTributos().getPercentualMva().divide(BigDecimal.valueOf(100))));
        baseCalcST = baseCalcST.setScale(2, RoundingMode.HALF_DOWN);

        return baseCalcST;
    }

    private BigDecimal calculaIcmsComDescontoIncondicional(BigDecimal baseCalculoInicial) {
        BigDecimal baseCalc = Biblioteca.subtrai(baseCalculoInicial, getTributos().getDesconto());

        return aplicarReducaoBaseCalculoST(baseCalc);
    }

    private BigDecimal calculaIcmsComDescontoCondicional(BigDecimal baseCalculoInicial) {
        BigDecimal baseCalc = baseCalculoInicial.add(getTributos().getDesconto());

        return aplicarReducaoBaseCalculoST(baseCalc);
    }

    private BigDecimal aplicarReducaoBaseCalculoST(BigDecimal baseCalculoInicial) {
        BigDecimal reducao = baseCalculoInicial.multiply(getTributos().getPercentualReducaoSt()).divide(BigDecimal.valueOf(100));
        baseCalculoInicial = baseCalculoInicial.subtract(reducao);
        baseCalculoInicial = baseCalculoInicial.setScale(2, RoundingMode.DOWN);

        return baseCalculoInicial;
    }
}
