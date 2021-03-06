// Copyright (c) 2003-present, Jodd Team (http://jodd.org)
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are met:
//
// 1. Redistributions of source code must retain the above copyright notice,
// this list of conditions and the following disclaimer.
//
// 2. Redistributions in binary form must reproduce the above copyright
// notice, this list of conditions and the following disclaimer in the
// documentation and/or other materials provided with the distribution.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
// AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
// ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
// LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
// CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
// SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
// INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
// CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
// ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
// POSSIBILITY OF SUCH DAMAGE.

package jodd.proxetta;

import jodd.proxetta.fixtures.data.Hero;
import jodd.proxetta.fixtures.data.HeroProxyAdvice;
import jodd.proxetta.fixtures.data.HeroProxyAdvice2;
import jodd.proxetta.impl.ProxyProxetta;

import jodd.proxetta.impl.WrapperProxetta;
import jodd.proxetta.impl.WrapperProxettaBuilder;
import jodd.proxetta.pointcuts.AllRealMethodsPointcut;
import jodd.util.ClassUtil;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnnTest {

	@Test
	void testMethodAnnotationsProxy() {
		ProxyProxetta proxetta = ProxyProxetta
				.withAspects(
					new ProxyAspect(HeroProxyAdvice.class,
							new AllRealMethodsPointcut() {
								@Override
								public boolean apply(MethodInfo methodInfo) {
									if (!methodInfo.isTopLevelMethod()) {
										return false;
									}
									return super.apply(methodInfo);
								}
							}))
				//.setDebugFolder("/Users/igor/")
				;

		ProxettaBuilder proxettaBuilder = proxetta.builder();
		proxettaBuilder.setTarget(Hero.class);
		proxetta.setVariableClassName(true);
		Hero hero = (Hero) proxettaBuilder.newInstance();

		assertEquals("BatmanHero37W88.3CatWoman99speeeeedXRAYnull", hero.name());
	}

	@Test
	void testClassAnnotationsProxy() {
		ProxyProxetta proxetta = ProxyProxetta
				.withAspects(
					new ProxyAspect(HeroProxyAdvice2.class,
							new AllRealMethodsPointcut() {
								@Override
								public boolean apply(MethodInfo methodInfo) {
									if (!methodInfo.isTopLevelMethod()) {
										return false;
									}
									return super.apply(methodInfo);
								}
							}))
				//.setDebugFolder("/Users/igor/")
				;

		ProxettaBuilder proxettaBuilder = proxetta.builder();
		proxettaBuilder.setTarget(Hero.class);
		proxetta.setVariableClassName(true);
		Hero hero = (Hero) proxettaBuilder.newInstance();

		assertEquals("SilverHero89W99.222None1000speeeeedXRAYnull", hero.name());
	}

	@Test
	void testMethodAnnotationsWrapper() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		WrapperProxetta proxetta = WrapperProxetta
				.withAspects(
					new ProxyAspect(HeroProxyAdvice.class,
							new AllRealMethodsPointcut() {
								@Override
								public boolean apply(MethodInfo methodInfo) {
									if (!methodInfo.isTopLevelMethod()) {
										return false;
									}
									return super.apply(methodInfo);
								}
							}))
				//.setDebugFolder("/Users/igor/")
				;

		WrapperProxettaBuilder proxettaBuilder = proxetta.builder();
		proxettaBuilder.setTarget(Hero.class);
		proxetta.setVariableClassName(true);
		Object hero = proxettaBuilder.newInstance();

		Method nameMethod = hero.getClass().getMethod("name");
		assertEquals("BatmanHero37W88.3CatWoman99speeeeedXRAYnull", nameMethod.invoke(hero));
	}

	@Test
	void testClassAnnotationsWrapper() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		WrapperProxetta proxetta = WrapperProxetta
				.withAspects(
					new ProxyAspect(HeroProxyAdvice2.class,
							new AllRealMethodsPointcut() {
								@Override
								public boolean apply(MethodInfo methodInfo) {
									if (!methodInfo.isTopLevelMethod()) {
										return false;
									}
									return super.apply(methodInfo);
								}
							}))
				//.setDebugFolder("/Users/igor/")
				;

		WrapperProxettaBuilder proxettaBuilder = proxetta.builder();
		proxettaBuilder.setTarget(Hero.class);
		proxetta.setVariableClassName(true);
		Object hero = proxettaBuilder.newInstance();

		Method nameMethod = hero.getClass().getMethod("name");
		assertEquals("SilverHero89W99.222None1000speeeeedXRAYnull", nameMethod.invoke(hero));
	}

}
