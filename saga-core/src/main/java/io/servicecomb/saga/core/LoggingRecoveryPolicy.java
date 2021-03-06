/*
 * Copyright 2017 Huawei Technologies Co., Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.servicecomb.saga.core;

import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LoggingRecoveryPolicy implements RecoveryPolicy {
  private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
  private final RecoveryPolicy recoveryPolicy;

  LoggingRecoveryPolicy(RecoveryPolicy recoveryPolicy) {
    this.recoveryPolicy = recoveryPolicy;
  }

  @Override
  public void apply(SagaTask task, SagaRequest request) {
    log.info("Starting request id={} for service {}", request.id(), request.serviceName());
    recoveryPolicy.apply(task, request);
    log.info("Completed request id={} for service {}", request.id(), request.serviceName());
  }
}
