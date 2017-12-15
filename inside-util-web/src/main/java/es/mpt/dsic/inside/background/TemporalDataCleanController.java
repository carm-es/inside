/* Copyright (C) 2016 MINHAP, Gobierno de España
   This program is licensed and may be used, modified and redistributed under the terms
   of the European Public License (EUPL), either version 1.1 or (at your
   option) any later version as soon as they are approved by the European Commission.
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
   or implied. See the License for the specific language governing permissions and
   more details.
   You should have received a copy of the EUPL1.1 license
   along with this program; if not, you may find it at
   http://joinup.ec.europa.eu/software/page/eupl/licence-eupl */

package es.mpt.dsic.inside.background;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import es.mpt.dsic.inside.service.TemporalDataBusinessService;

public class TemporalDataCleanController extends QuartzJobBean {

	protected static final Log logger = LogFactory.getLog(TemporalDataCleanController.class);
	
	private TemporalDataBusinessService temporalDataBusinessService;
	
	private ApplicationContext appCtx;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.debug("Inicio TemporalDataCleanController.executeInternal");
		
		SchedulerContext schedCtx;
		try {
			schedCtx = context.getScheduler().getContext();

			appCtx = (ApplicationContext) schedCtx.get("applicationContext");
			temporalDataBusinessService = (TemporalDataBusinessService) appCtx.getBean("temporalDataBusinessService");
			
			temporalDataBusinessService.cleanDirectory();
			
			logger.debug("Fin TemporalDataCleanController.executeInternal");
		} catch (SchedulerException e) {
			logger.error("TemporalDataCleanController-executeInternal error al cargar datos: " + e.getMessage(), e);
		}
	}

}
