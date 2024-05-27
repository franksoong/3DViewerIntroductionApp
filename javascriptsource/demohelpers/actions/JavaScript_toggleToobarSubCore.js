// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
import "mx-global";
import { Big } from "big.js";

// BEGIN EXTRA CODE
const HIDDENCLASS = 'hidden';
const DATAATTR = 'data-contentid';
const get = (selector, parent)=>{
	const root = parent || document;
	return root.querySelector(selector);
}
const hasClass = (el, className)=>{
	return el.classList.contains(className);
}
const toggleClass = (el, className) =>{
	const flag = hasClass(el, className);
	if (flag) {
		el.classList.remove(className);
	} else {
		el.classList.add(className);
	}
	return flag;
}

// END EXTRA CODE

/**
 * @param {MxObject} appContextEntity
 * @param {string} contendId
 * @returns {Promise.<void>}
 */
export async function JavaScript_toggleToobarSubCore(appContextEntity, contendId) {
	// BEGIN USER CODE
	let selector = `.toolbar.sub`;
	let el = get(selector);
	if(!el){
		console.warn(`${selector} was not exist`);
		return;
	}


	// case empty contendId - hide
	// case valid contendId - toggle
	if(!contendId){
		console.log(`${selector} should be invisible`);
		el.classList.add(HIDDENCLASS);
	}else{
		// toggle
		// case1: same contendId, apply toggle
		// case2: not same contendId, we apply show
		let currentContendId = el.getAttribute(DATAATTR);
		if(currentContendId === contendId){
			let currentVisibility = get(`${selector}:not(.${HIDDENCLASS})[${DATAATTR}='${contendId}']`);
			if(currentVisibility){
				console.log(`${selector} should be invisible`);
				el.classList.add(HIDDENCLASS);
			}else{
				console.log(`${selector} should be visible`);
				el.classList.remove(HIDDENCLASS);
				el.setAttribute(DATAATTR, contendId);
			}
		}else{
			console.log(`${selector} should be visible`);
			el.classList.remove(HIDDENCLASS);
			el.setAttribute(DATAATTR, contendId);
		}
	}
	// END USER CODE
}
