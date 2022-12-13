package com.jdc.app.domain;

import java.time.LocalDateTime;

public record TransferLog(
				String from, String to,
				int amount,
				LocalDateTime transferDate,
				int fromAmount,
				int toAmount
		) {

}
