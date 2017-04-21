/**
* Copyright (c) 2000-present Liferay, Inc. All rights reserved.
*
* This library is free software; you can redistribute it and/or modify it under
* the terms of the GNU Lesser General Public License as published by the Free
* Software Foundation; either version 2.1 of the License, or (at your option)
* any later version.
*
* This library is distributed in the hope that it will be useful, but WITHOUT
* ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
* FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
* details.
*/
import UIKit
import WebKit


open class PortletDisplayView_default: BaseScreenletView, PortletDisplayViewModel, WKUIDelegate {

	open var jsFile: String?

	//MARK: Outlets

	open var webView: WKWebView?

	open var userContentController: WKUserContentController?


	//MARK: BaseScreenletView

	override open var progressMessages: [String: ProgressMessages] {
		return [
			BaseScreenlet.DefaultAction :
				[
					.working : LocalizedString(
						"default", key: "portletdisplay-loading-message", obj: self),
					.failure : LocalizedString(
						"default", key: "portletdisplay-loading-error", obj: self)
			]
		]
	}

	override open func onCreated() {
		super.onCreated()

		let config = WKWebViewConfiguration.noCacheConfiguration

		webView = WKWebView(frame: self.frame, configuration: config)

		webView?.injectCookies()
		webView?.injectViewportMetaTag()
		webView?.uiDelegate = self

		addWebView()
	}

	override open func createProgressPresenter() -> ProgressPresenter {
		return DefaultProgressPresenter()
	}


	//MARK: PortletDisplayViewModel

	open var portletUrl: URL? {
		get {
			return self.portletUrl
		}
		set {
			webView?.load(URLRequest(url: newValue!))
		}
	}


	public var injectedJsFile: String? {
		get {
			return self.injectedJsFile
		}
		set {
			webView?.loadJs(file: newValue!)
		}
	}


	//MARK: WKUIDelegate

	public func webView(_ webView: WKWebView, runJavaScriptAlertPanelWithMessage message: String, initiatedByFrame frame: WKFrameInfo, completionHandler: @escaping () -> Void) {
		completionHandler()
	}


	//MARK: Public methods

	open func addWebView() {
		webView?.translatesAutoresizingMaskIntoConstraints = false

		addSubview(webView!)

		let top = NSLayoutConstraint(item: webView!, attribute: .top, relatedBy: .equal,
		                             toItem: self, attribute: .top, multiplier: 1, constant: 0)
		let bottom = NSLayoutConstraint(item: webView!, attribute: .bottom, relatedBy: .equal,
		                                toItem: self, attribute: .bottom, multiplier: 1, constant: 0)
		let leading = NSLayoutConstraint(item: webView!, attribute: .leading, relatedBy: .equal,
		                                 toItem: self, attribute: .leading, multiplier: 1, constant: 0)
		let trailing = NSLayoutConstraint(item: webView!, attribute: .trailing, relatedBy: .equal,
		                                  toItem: self, attribute: .trailing, multiplier: 1, constant: 0)

		NSLayoutConstraint.activate([top, bottom, leading, trailing])
	}
}